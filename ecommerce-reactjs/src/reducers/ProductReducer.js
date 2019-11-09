import {GET_PRODUCT_NEW,GET_ALL_PRODUCT, GET_PRODUCT_DETAIL} from '../actions/types';
const initialState={
    product_new:[],
    product:[],
    productdetail:[]
}
export default function(state= initialState, action){
    switch(action.type){
        case GET_PRODUCT_NEW:
            return{
                ...state,
                product_new:action.payload
            };
        case GET_ALL_PRODUCT:
            return{
                ...state,
                product:action.payload
            };
        case GET_PRODUCT_DETAIL:
                
            return{
                ...state,
                productdetail:action.payload

            };
        default:
            return state;
    }
}