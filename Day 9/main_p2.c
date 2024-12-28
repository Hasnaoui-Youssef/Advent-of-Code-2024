#include<stdio.h>
#include<stdint.h>
#define ATOI(x) (x - '0')
#define MAX_SIZE 2000000

typedef long long ll;

long offset = 0;
ll id = 0;
long pair = 0;

typedef struct{
    ll pos;
    ll length;
} data ;
data data_arr[MAX_SIZE] = {};
data empty_arr[MAX_SIZE] = {};


void fitting_free_space(ll index){
    for(int i = 0; i < index ; i++){
        if(empty_arr[i].length >= data_arr[index].length){
            empty_arr[i].length -= data_arr[index].length;
            data_arr[index].pos = empty_arr[i].pos;
            if(empty_arr[i].length > 0){
                empty_arr[i].pos += data_arr[index].length;
            }
            return;
        }
    }
}

long main(int argc, char** argv){
    FILE* file;
    file = fopen("input.txt", "r");
    if(!file){
        printf("Nejemnech nakraw l fichier\n");
        return 1;
    }
    fseek(file, 0, SEEK_END);
    ll file_size = ftell(file);
    fseek(file, 0, SEEK_SET);
    for(int i = 0; i < MAX_SIZE; i++){
        fseek(file, i , SEEK_SET);
        char ch = fgetc(file);
        if(ch == EOF){
            printf("Wselna l ekher l fichier\n");
            break;
        }
        if(pair == 0){
            data_arr[id].pos = offset;
            data_arr[id].length = ATOI(ch);
            pair = 1;
        }else{
            empty_arr[id].pos = offset;
            empty_arr[id].length = ATOI(ch);
            pair = 0;
            id++;
        }
        offset += ATOI(ch);
    }
    fclose(file);
    for(ll i = id ; i >= 0; i --){
        fitting_free_space(i);
    }
    ll sum = 0;
    for(long i = 1; i <= id; i++){
        for(int j = data_arr[i].pos; j < data_arr[i].pos  + data_arr[i].length; j++){
            sum += i * j;
        }
    }
    printf("\nTotal sum : %lld", sum);
    return 0;
}