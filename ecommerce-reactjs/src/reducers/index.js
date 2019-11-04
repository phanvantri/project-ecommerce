import { combineReducers } from "redux";
import errorsReducer from "./errorsReducer";
import CategoryListReducer from "./CategoryListReducer";
import ProductReducer from "./ProductReducer";

export default combineReducers({
  //
  errors: errorsReducer,
  project_Category: CategoryListReducer,
  product_new: ProductReducer
});