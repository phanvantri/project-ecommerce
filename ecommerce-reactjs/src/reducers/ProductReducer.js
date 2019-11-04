import {GET_PRODUCT_NEW,GET_ALL_PRODUCT} from '../actions/types';
const initialState={
    product_new:[],
    lstproduct:[]
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
                lstproduct:action.payload
            };
        default:
            return state;
    }
}