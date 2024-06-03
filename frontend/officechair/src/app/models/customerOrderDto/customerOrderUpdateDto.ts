import { User } from "../user";

export class CustomerOrderUpdateDto {
    
    orderId!: String;
	dateOfCreation!: Date;
	totalBill!: number;
	payed:boolean;
	timeOfPayment!: Date;
	sent: boolean;
	timeWhenSent!: Date;
	
	customer!: User;
	employee!: User;

	constructor() {
		this.payed = false;
		this.sent = false;
	}
}