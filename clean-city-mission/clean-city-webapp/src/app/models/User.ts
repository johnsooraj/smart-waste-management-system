import { UserAddress } from './Address';

export class UserDetails {
    id: string;
    name: string;
    age: number;
    phone: string;
    binCredit: number;
    timestamp: Date;
    createDate: Date;
    flag: number;
    userAddress: UserAddress;
}