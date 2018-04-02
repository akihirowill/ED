#include <stdlib.h>
#include "inteiro.h"
#include "implA.h"

struct InteiroImpl {
	int valor;
};

static Inteiro *incrementar(Inteiro *self) {
	Inteiro *novo = ImplA_novo();
	novo->priv->valor = self->priv->valor + 1;
	return novo;
}

static Inteiro *somar(Inteiro *self, Inteiro *outro) {
	Inteiro *novo = ImplA_novo();
	novo->priv->valor = self->priv->valor + outro->priv->valor;
	return novo;
}

static void repetir(Inteiro *self, void (*funcao)(void *), void *arg) {
	for (int i = 0; i < self->priv->valor; i++)
		funcao(arg);
}

Inteiro *ImplA_novo() {
	Inteiro *novo = (Inteiro *) malloc(sizeof(Inteiro));
	novo->priv = (InteiroImpl *) malloc(sizeof(InteiroImpl));
	novo->priv->valor = 0;
	novo->incrementar = incrementar;
	novo->somar = somar;
	novo->repetir = repetir;
	return novo;
}
