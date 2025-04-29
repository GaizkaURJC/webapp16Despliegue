
export interface CreateRequestUserDTO {
    id: number | null; 
    name: string;
    email: string;
    password: string;
    phone: string;
    roles: string[]; 
}