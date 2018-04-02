#include <stdlib.h>
#include "inteiro.h"
#include "implB.h"

struct InteiroImpl {
	Inteiro *predecessor;
};

static Inteiro *incrementar(Inteiro *self) {
	Inteiro *novo = ImplB_novo();
	novo->priv->predecessor = self;
	return novo;
}

static void incrementa_varias_vezes(void *arg) {
	Inteiro **novo_ptr = (Inteiro **) arg;
	*novo_ptr = (*novo_ptr)->incrementar(*novo_ptr);
}

static Inteiro *somar(Inteiro *self, Inteiro *outro) {
	Inteiro *novo = ImplB_novo();
	novo->priv->predecessor = self->priv->predecessor;
	outro->repetir(outro, incrementa_varias_vezes, (void *)&novo);
	return novo;
}

static void repetir(Inteiro *self, void (*funcao)(void *), void *arg) {
	if (self->priv->predecessor == NULL)
		return;
	funcao(arg);
	self->priv->predecessor->repetir(self->priv->predecessor, funcao, arg);
}

Inteiro *ImplB_novo() {
	Inteiro *novo = (Inteiro *) malloc(sizeof(Inteiro));
	novo->priv = (InteiroImpl *) malloc(sizeof(InteiroImpl));
	novo->priv->predecessor = NULL;
	novo->incrementar = incrementar;
	novo->somar = somar;
	novo->repetir = repetir;
	return novo;
}
