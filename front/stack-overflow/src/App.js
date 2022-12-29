import React from 'react';
import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import TagsPage from './pages/TagsPage/index';
import Users from './pages/Users/index';
import GlobalStyle from './assets/GlobalStyle';
import styled from 'styled-components';

const App = () => {
  let bgColor;
  return (
    <Root color={bgColor}>
      <GlobalStyle />
      <TagsPage />
    </Root>
  );
};
const Root = styled.div`
  background-color: ${(props) => props.color};
`;

export default App;
