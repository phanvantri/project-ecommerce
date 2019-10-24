import {GET_PRODUCT_NEW} from '../actions/types';
const initialState={
    product_new:[]
}
export default function(state= initialState, action){
    switch(action.type){
        case GET_PRODUCT_NEW:
            return{
                ...state,
                product_new:action.payload
            };
        default:
            return state;
    }
}