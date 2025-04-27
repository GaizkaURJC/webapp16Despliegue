
export interface CreateRequestUserDTO {
    id: number | null; // ID can be null for new users
    name: string;
    email: string;
    password: string;
    phone: string;
    roles: string[]; // List of roles assigned to the user
}