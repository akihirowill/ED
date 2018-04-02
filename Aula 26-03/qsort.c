#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <stdbool.h>

#define NELEM(vetor) (sizeof((vetor))/sizeof((vetor)[0]))

int comparar(const void *a, const void *b){
	const int *int_a = (const int *)a;
	const int *int_b = (const int *)b;

	return *int_a - *int_b;
}

bool existe(int *v,	int min_idx,int max_idx,int valor){
	int meio =	abs((max_idx-min_idx)/2);


	if(v[meio] == valor){
		printf("%s\n","encontrou_no_meio" );
		return true;
	}

	if(valor > v[meio]){
		for(int i = meio; i< max_idx;i++){
			if(v[i] == valor){
				printf("%s%d\n","Buscou_do_meio_para_frente_e_existe",i);
				return true;
			}
		}
	}else {
		for(int i = min_idx; i < v[meio];i++){
			if(v[i] == valor){
				printf("%s%d\n","Buscou_do_meio_para_tras_e_existe",i);
				return true;
			}
		}
	}

	printf("%s\n","nao_encotrou");
	return false;

}

int main(){
	int v[] = {123,537,342,155,853};
	int min_idx = 0, max_idx = sizeof(v)/sizeof(v[0]);

	qsort(v,NELEM(v),sizeof(v[0]),comparar);
	for(int i = 0;i < NELEM(v);i++){
		printf("%d\n",v[i]);
	}
	existe(v,min_idx,max_idx,342);

	return 0;
}	