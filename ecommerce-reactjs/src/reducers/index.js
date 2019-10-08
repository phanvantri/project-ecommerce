import { combineReducers } from "redux";
import errorsReducer from "./errorsReducer";
import CategoryListReducer from "./CategoryListReducer";

export default combineReducers({
  //
  errors: errorsReducer,
  project_Category: CategoryListReducer
});