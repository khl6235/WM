import React, {Component} from 'react';
import ApiService from '../../ApiService';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import CreateIcon from '@material-ui/icons/Create';
import DeleteIcon from '@material-ui/icons/Delete';

class FormListComponent extends Component{

    constructor(props){
        super(props);

        this.state = {
            forms: [],
            message: null
        }
    }

    componentDidMount(){
        this.loadFormList();
    }

    loadFormList = () =>{
        ApiService.fetchForms()
        .then(res => {
        //     console.log('then enter!');
            console.log(res);
            // this.setState({
            //     forms: res.data
            // })
            // console.log(res);
        })
        .catch(err => {
            console.log('loadFormList() Error!', err.response);
        })
    }

    deleteForm = (formIdx) => {
        ApiService.deleteUser(formIdx)
        .then(res => {
            this.setState({
                message: 'User Deleted Successfully.'
            });
            this.setState({
                forms: this.state.forms.filter(form =>
                    form.formIdx !== formIdx)
            });
        })
        .catch(err => {
            console.log('deleteUser() Error!', err);
        })
    }

    editUser = (ID) =>{
        window.localStorage.setItem("userID", ID);
        this.props.history.push('/edit-user');
    }

    addUser = () =>{
        window.localStorage.removeItem("userID");
        this.props.history.push('/add-user');
    }

    render(){
        return(
            <div>
                <Typography variant="h4" style={style}>Form List</Typography>
                <Button variant="contained" color="primary" onClick={this.addUser}>Add User</Button>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>formIdx</TableCell>
                            <TableCell>Title</TableCell>
                            <TableCell>userIdx</TableCell>
                            <TableCell>createdAt</TableCell>
                            {/* <TableCell align="right">Edit</TableCell>
                            <TableCell align="right">Delete</TableCell> */}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.state.forms.map(form=>
                            <TableRow key={form.formIdx}>
                                <TableCell component="th" scope="form">{form.formIdx}</TableCell>
                                <TableCell>{form.title}</TableCell>
                                <TableCell>{form.userIdx}</TableCell>
                                <TableCell>{form.createdAt}</TableCell>
                                {/* <TableCell align="right" onClick={()=> this.editUser(user.id)}>
                                    <CreateIcon/>
                                </TableCell>
                                <TableCell align="right" onClick={()=> this.deleteUser(user.id)}>
                                    <DeleteIcon/>
                                </TableCell> */}
                            </TableRow>
                            )}
                    </TableBody>
                </Table>
            </div>
        );
    }
}

const style={
    display: 'flex',
    justifyContent: 'center'
}

export default FormListComponent;