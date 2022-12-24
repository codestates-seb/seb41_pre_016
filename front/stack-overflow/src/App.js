import React from "react";
import "./App.css";
import LeftSideBar from "./LeftSideBar/LeftSideBar";
import Header from "./Header/Header";
import styled from "styled-components";
import HeaderDropDown from "./Header/HeaderDropDown";
import Login from "./Login/Login";
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
    <RootDiv>
      <Header />
      <Login />
    </RootDiv>
  );
};

export default App;
