import { UserDTO } from "../dtos/user.dto";

export interface LoginResponse {
    token: string;
    user: UserDTO;
  }
  