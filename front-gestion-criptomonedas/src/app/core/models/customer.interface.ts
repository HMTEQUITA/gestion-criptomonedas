import { CryptoInterface } from "./crypto.interface";

export interface CustomerInterface{
    id:number;
    name:string;
    surname:string
    country:string
    cryptocurrencies:CryptoInterface[]
}