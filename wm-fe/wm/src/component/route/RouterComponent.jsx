import React from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import FormListComponent from "../user/FormListComponent";
import AddUserComponent from "../user/AddUserComponent";
import EditUserComponent from "../user/EditUserComponent";
import LoginComponent from "../user/LoginComponent";

const AppRouter = () =>{
    return(
        <div style={style}>
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={LoginComponent}/>
                    <Route path="/formlist" component={FormListComponent}/>
                    <Route path="/add-user" component={AddUserComponent}/>
                    <Route path="edit-user" component={EditUserComponent}/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

const style={
    marginTop: '20px'
}

export default AppRouter;