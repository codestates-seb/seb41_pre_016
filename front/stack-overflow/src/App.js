import React, { useState, useEffect } from "react";
import "./App.css";
import axios from "axios";
import Questions from "./pages/Questions/Questions";
import { Reset } from "styled-reset";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header/Header";
import styled from "styled-components";
import Login from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import SignupPage from "./pages/SignupPage";
import AskQuestionPage from "./pages/AskQuestionPage";
import Question from "./pages/Question";
import "./App.css";
import TagsPage from "./pages/TagsPage/index";
import Users from "./pages/Users/index";
import GlobalStyle from "./assets/GlobalStyle";

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
  margin: 0 auto;
  position: relative;
  flex: 1 0 auto;
  text-align: left;
`;

const App = () => {
  return (
    <BrowserRouter>
      <GlobalStyle />
      <Reset />
      <RootDiv>
        <Header />
      </RootDiv>
      <MainDiv>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/questions/:questionId" element={<Question />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/questions/ask" element={<AskQuestionPage />} />
        </Routes>
      </MainDiv>
    </BrowserRouter>
  );
};

export default App;
