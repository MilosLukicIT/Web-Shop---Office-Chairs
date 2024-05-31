import { User } from "../user";

export class CustomerOrderViewDto {
    
    orderId!: String;
	dateOfCreation!: Date;
	totalBill!: number;
	payed!:Boolean;
	timeOfPayment!: Date;
	sent!: Boolean;
	timeWhenSent!: Date;
	
	customer!: User;
	employee!: User;
}