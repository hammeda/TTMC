export interface User {
    id: number;
    version: number;
    firstName: string;
    lastName: string;
    email: string;
    password: string;
    roles: string[];
}