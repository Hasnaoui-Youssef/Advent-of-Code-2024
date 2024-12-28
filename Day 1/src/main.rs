use std::env;
use std::fs;
use std::collections::HashMap;

fn main() {
    let args : Vec<String> = env::args().collect();
    let (ysar, ymin) = read_data(&args[1]);
    let mut hm : HashMap<i32, i32> = HashMap::new();
    for x in ymin {
        *hm.entry(x).or_default() += 1;
    }
    let mut sum : i32 = 0;
    for x  in ysar{
        sum += *hm.get(&x).unwrap_or(&0) * x;
    }
    println!("{sum}");
}

fn read_data_sorted(file_path : &str) -> (Vec<i32> ,Vec<i32>){
    let content = fs::read_to_string(file_path)
        .expect("Nejemch nakra l fichier");
    let mut first : Vec<i32> = Vec::new();
    let mut second : Vec<i32> = Vec::new();
    for line in content.lines() {
        let parts : Vec<&str> = line.split_whitespace().collect();
        if let [left, right] = &parts[..]{
            first.push(left.parse::<i32>().unwrap());
            second.push(right.parse::<i32>().unwrap());
        }
    }
    first.sort();
    second.sort();
    (first, second)
}

fn read_data(file_path : &str) -> (Vec<i32>, Vec<i32>){
    let content = fs::read_to_string(file_path)
        .expect("Nejemch nakra l fichier");
    let mut first : Vec<i32> = Vec::new();
    let mut second : Vec<i32> = Vec::new();
    for line in content.lines() {
        let parts : Vec<&str> = line.split_whitespace().collect();
        if let [left, right] = &parts[..]{
            first.push(left.parse::<i32>().unwrap());
            second.push(right.parse::<i32>().unwrap());
        }
    }
    (first, second)
}