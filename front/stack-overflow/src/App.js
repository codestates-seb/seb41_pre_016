import React from "react";
import "./App.css";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header/Header";
import styled from "styled-components";
import Login from "./pages/LoginPage";
import MainPage from "./pages/MainPage";
import SignupPage from "./pages/SignupPage";
import AskQuestionPage from "./pages/AskQuestionPage";
import { Reset } from "styled-reset";
import Question from "./pages/Question";
import TagsPage from "./pages/TagsPage/index";
import Users from "./pages/Users";
import UserPage from "./pages/UserPage";

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
  return (
    <BrowserRouter>
      <Reset />
      <RootDiv>
        <Header />
      </RootDiv>
      <MainDiv>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/questions/:questionId" element={<Question />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route path="/questions/ask" element={<AskQuestionPage />} />
          <Route path="/tags" element={<TagsPage />} />
          <Route path="/users" element={<UserPage />} />
        </Routes>
      </MainDiv>
    </BrowserRouter>
  );
};

export default App;
