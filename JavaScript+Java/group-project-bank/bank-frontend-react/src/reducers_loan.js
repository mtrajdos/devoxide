import { NEW_LOAN, RETURN_MORTGAGE } from "./actions";

const initalState = {
    loan: {},
    mortgage: {}
}

export const loans = (state = initalState, action ) => {
    const { type, payload } = action;

    switch(type) {
        case NEW_LOAN: {
            const { loan } = payload;
            return{
                ...state,
                loan: loan
            }
        }
        case RETURN_MORTGAGE: {
            const { checkedMortgage } = payload;
            console.log("Mortgage returned")
            return {
                ...state,
                mortgage: checkedMortgage
            }
        }
        default:
            return state;
    }
    
} 