import React, {Component} from 'react';
import ApiService from '../../ApiService';

import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { TextField } from '@material-ui/core';

class LoginComponent extends Component{

    constructor(props){
        super(props);

        this.state = {
            id: '',
            password: '',
            message: null
            // buttonChange: 'loginBtn'
        };
    }

    onChange = (e) =>{
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    // componentDidMount(){
    //     this.testLogin();
    // }

    // testLogin = () => {
    //     ApiService.fetchUsers()
    //     .then(res => {
    //         this.setState({
    //             users: res.data
    //         })
    //     })
    //     .catch(err => {
    //         console.log('reloadUserList() Error!', err);
    //     })
    // }

    logIn = () => {
        if(this.state.id.length > 0 && this.state.password.length > 0){
            console.log("로그인 성공");
            this.props.history.push('/formlist');
        }
        else{
            alert('정보를 입력해주세요.');
        }
    }

    render(){
        return(
            <div style={style2}>
                <Typography variant="h5"  style={style}>LOGIN</Typography>
                <form style={formContainer}>
                    <TextField type="text" placeholder="ID" fullWidth margin="normal" 
                    value={this.state.id} name="id" onChange={this.onChange}/>
                    
                    <TextField type="password" placeholder="PASSWORD" fullWidth margin="normal" 
                    value={this.state.password} name="password" onChange={this.onChange}/>

                    <Button variant="contained" color="primary" onClick={this.logIn}>login</Button>
                </form>
            </div>
        );
    }
}

const style2 = {
    width: '300px',
    margin: '0 auto'
}

const formContainer = {
    marginTop: '50px',
    display: 'flex',
    flexFlow: 'row wrap',
    justifyContent: 'center'
    
}

const style={
    marginTop: '60px',
    display: 'flex',
    justifyContent: 'center'
}

export default LoginComponent;