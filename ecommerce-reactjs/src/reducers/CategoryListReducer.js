import {GET_PROJECT_CATEGORY} from '../actions/types'

const initialState ={
    project_Category:[]
}
export default function(state= initialState ,action){
    switch(action.type){
        case GET_PROJECT_CATEGORY:
            return{
            ...state,
            project_Category:action.payload
            };
        default:
            return state;

    }
}