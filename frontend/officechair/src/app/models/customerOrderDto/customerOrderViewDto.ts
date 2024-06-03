import { User } from "../user";

export class CustomerOrderViewDto {
    
    orderId!: string;
	dateOfCreation!: Date;
	totalBill!: number;
	payed!:boolean;
	timeOfPayment!: Date;
	sent!: boolean;
	timeWhenSent!: Date;
	
	customer!: User;
	employee!: User;
}