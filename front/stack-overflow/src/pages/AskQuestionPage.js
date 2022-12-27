import styled from "styled-components";
import { BlueButton } from "../StyledTag/BlueButton";
import AskQuestionHeader from "../components/AskQuestion/AskQuestionHeader";
import AskQuestionTitle from "../components/AskQuestion/AskQuestionTitle";
import AskQuestionBody from "../components/AskQuestion/AskQuestionBody";
import AskQuestionTag from "../components/AskQuestion/AskQuestionTag";
import { useAskQuestionStore } from "../store/askQuestionZustand";
import { useState } from "react";
const AskQuestionPage = () => {
  const { title, setTitle, content, setContent, tags, setTags } =
    useAskQuestionStore();
  const postRequestButton = () => {
    setTitle(document.getElementById("title").value);
    setContent(document.getElementById("body").value);
    console.log("title", title);
    console.log("content", content);
  };
  const ContainerDiv = styled.div`
    max-width: 100%;
    justify-content: center;
    margin: 0 0 48px 0;
    width: 100%;
    background: none;
    display: flex;
    position: relative;
    text-align: left;
    h1,
    h2,
    h3 {
      line-height: 1.3;
      margin: 0 0 1em;
    }
  `;
  const ContentDiv = styled.div`
    min-height: 750px;
    overflow: visible;
    width: 100%;
    max-width: 1264px;
    margin: 0;
    background-color: transparent;
    border-left: 0;
    border-right: 0;
    padding: 0 24px 24px 24px;
    div:first-child {
      width: 100% !important;
      box-sizing: border-box !important;
    }
  `;
  const MainDiv = styled.div`
    margin-bottom: 20px;
    :first-child {
      display: flex !important;
      flex-direction: column !important;
    }
  `;
  const ContentHeadDiv = styled.div`
    height: 130px;
    background-image: url(https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368);
    background-repeat: no-repeat !important;
    width: 100% !important;
    background-position: right bottom !important;
    display: flex !important;
    text-align: left;
    margin: 20px 0;
    h1 {
      font-weight: 600 !important;
      font-size: 27px !important;
      margin-top: 24px !important;
      line-height: 1.3;
      margin: 0 0 1em;
    }
  `;
  return (
    <ContainerDiv>
      <ContentDiv>
        <div>
          <MainDiv>
            <div id="d-flex fd-column js-write-question-notice">
              <ContentHeadDiv>
                <h1>Ask a public question</h1>
              </ContentHeadDiv>
              <AskQuestionHeader />
              <AskQuestionTitle />
              <AskQuestionBody />
              <AskQuestionTag />
            </div>
          </MainDiv>
          <BlueButton onClick={postRequestButton}>
            Post your question
          </BlueButton>
        </div>
      </ContentDiv>
    </ContainerDiv>
  );
};
export default AskQuestionPage;
