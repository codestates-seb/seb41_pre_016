import React from 'react';
import './App.css';
import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import TagsPage from './pages/TagsPage/index';
import Users from './pages/Users/index';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import styled from 'styled-components';
import Login from './pages/LoginPage';
import MainPage from './pages/MainPage';
import SignupPage from './pages/SignupPage';
import AskQuestionPage from './pages/AskQuestionPage';
const RootDiv = styled.div`
  z-index: -100;
  display: flex;
  padding-top: 50px;
`;
const MainDiv = styled.div`
  max-width: 1264px;
  width: 100%;
  background: none;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  position: relative;
  flex: 1 0 auto;
  text-align: left;
`;
const App = () => {
  // const URL = process.env.REACT_APP_API_URL;

  // const [data, setData] = useState();
  // const [loading, setLoading] = useState(false);

  // useEffect(() => {
  //   const fetchData = async () => {
  //     setLoading(true);

  //     try {
  //       const response = await axios.get(URL);
  //       console.log(response);
  //       setData(response);
  //     } catch (e) {
  //       console.log(e);
  //     }
  //     setLoading(false);
  //   };

  //   fetchData();
  // }, []);

  return <Users />;
};

export default App;
