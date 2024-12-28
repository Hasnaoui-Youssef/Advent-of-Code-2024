#include<stdio.h>
#include<stdint.h>
#include<math.h>
#define ATOI(x) (x - '0')
#define MAX_SIZE 200000
#define INPUT_LEN 200000

long long arr[MAX_SIZE] = {};

void swap(long long* a, long long* b){
    int temp = *a;
    *a = *b;
    *b = temp;
}


long main(int argc, char** argv){
    FILE* file;
    file = fopen("input.txt", "r");
    if(!file){
        printf("Nejemnech nakraw l fichier\n");
        return 1;
    }
    fseek(file, 0, SEEK_END);
    long long file_size = ftell(file);
    fseek(file, 0, SEEK_SET);
    long offset = 0;
    long long id = 0;
    long pair = 0;
    for(int i = 0; i < INPUT_LEN; i++){
        fseek(file, i , SEEK_SET);
        char ch = fgetc(file);
        if(ch == EOF){
            printf("Wselna l ekher l fichier\n");
            break;
        }
        if(pair == 0){
            for (int j = offset; j < offset + ATOI(ch); j++){
                arr[j] = id;
            }
            pair = 1;
            id++;
        }else{
            for (int j = offset; j < offset + ATOI(ch); j++){
                arr[j] =-1;
            }
            pair = 0;

        }
        offset += ATOI(ch);
    }
    fclose(file);
    long num, dot;
    for(int i = offset - 1; i >= 0; i--){
        if(arr[i] != -1){
            num = i;
            break;
        }
    }
    for(long i = 0; i < offset; i++){
        if(arr[i] == -1){
            dot = i;
            break;
        }
    }
    while(dot < num){
        if(arr[num] == -1){
            num --;
        }else if(arr[dot] != -1){
            dot ++;
        }else{
            swap(arr + dot, arr + num);
            dot ++;
            num --;
        }
    }
    unsigned long long sum = 0;
    for(long i = 0; i < offset; i++){
        if(arr[i] != -1){
            sum += i * arr[i];
        }
    }
    printf("Total sum : %lld", sum);
    return 0;
}