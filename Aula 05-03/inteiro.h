#ifndef INTEIRO_H
#define INTEIRO_H

typedef struct Inteiro Inteiro;
typedef struct InteiroImpl InteiroImpl;

struct Inteiro {
	Inteiro * (*incrementar)(Inteiro *self);
	Inteiro * (*somar)(Inteiro *self, Inteiro *outro);
	void (*repetir)(Inteiro *self, void (*funcao)(void *), void *arg);

	InteiroImpl *priv;
};

#endif
