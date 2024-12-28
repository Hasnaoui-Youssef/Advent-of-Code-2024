import * as fs from 'fs';

fs.readFile('input.txt', 'utf8', (err, data) => {
    if(err){
        console.log(err);
        return;
    } 
    const a = data.trim().split('\r\n').filter((line)=> line.trim().length !== 0).map((line) => line.split('|'));
    const [x, y] = splitArray(a);
    const hm : Map<Number, Set<Number>> = new Map();
    x.forEach((e) => {
        const [k, v] = e;
        if(hm.has(Number(k))){
            hm.get(Number(k))?.add(Number(v));
        }else{
            hm.set(Number(k), new Set([Number(v)]));
        }
    });
    const compEL = y.map(element => {
        return element[0].split(',');
    });
    const total = compEL.filter((line) => {
        let varFlag = true;
        line.forEach((char, index) => {
            if( !varFlag || index === 0) { return ; }
            if(hm.has(Number(char))){
                const set = hm.get(Number(char));
                for(let i = index - 1; i >= 0 ; i--){
                    if(set?.has(Number(line[i]))){
                        //console.log(`Found violation at ${char} and ${line[i]}`);
                        varFlag = false;
                        break;
                    }
                }
            }
        })
        return !varFlag;
    }).map(line => {
       const n = line.length;
       let s : boolean = true;
       while(s){
        s = false;
        for(let i = 1; i < n; i++){
            for(let j = i - 1; j >= 0; j--){
                if(hm.has(Number(line[i]))){
                    if(hm.get(Number(line[i]))?.has(Number(line[j]))){
                        const aux = line[i];
                        line[i] = line[j];
                        line[j] = aux;
                        s = true;
                    }
                }
            }
        }
       }
       return line;
    })
    .map((line)=> {
        console.log(line);
        return Number(line[Math.floor(line.length / 2)]);
    }).reduce((acc, curr ) => {
        return acc + curr;
    }, 0);
    console.log(total);
    //console.log(hm);
})

function splitArray(arr : string[][]) : [string[][], string[][]]{
    const fh = arr.filter(e => e.length === 2);
    const sh = arr.filter(e => e.length === 1);
    return [fh, sh];
}
