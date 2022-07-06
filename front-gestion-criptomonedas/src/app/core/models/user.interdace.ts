import { RoleInterface } from "./role.interface";

export interface UserInterface{
    email:string;
    password:string;
    role:RoleInterface[]
}