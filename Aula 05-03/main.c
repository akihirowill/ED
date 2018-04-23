#include <stdio.h>

#include "inteiro.h"
#include "implA.h"
#include "implB.h"

void oi(void *arg) {
	printf("  oi\n");
}

int main() {
	Inteiro *a = ImplA_novo();
	a = a->incrementar(a);
	a = a->incrementar(a);
	printf("Abaixo, deve aparecer 'oi' duas vezes:\n");
	a->repetir(a, oi, NULL);
	printf("\n");

	return 0;
}
