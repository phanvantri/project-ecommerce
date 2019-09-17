import axios from 'axios';
import { async } from 'q';
export const addCategory=(category_add,history) => async dispatch =>{

    await axios.post("http://localhost:8080/api/addcategory",category_add);
    history.push("/");
}
