import AppRouter from './component/route/RouterComponent';
import React from 'react';
import NavBar from './component/route/NavBar';
import { Container } from '@material-ui/core';

function App() {
  return (
    <div>
      <NavBar />
      <Container>
        <AppRouter/>
      </Container>
    </div>
  );
}

export default App;
