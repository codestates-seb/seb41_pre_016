import styled from "styled-components";
import { useState } from "react";

const AskQuestionTitle = () => {
  const ContentMessageInput = styled.input`
    width: 100%;
    margin: 0;
    padding: 0.6em 0.7em;
    border: 1px solid var(--bc-darker);
    border-radius: 3px;
    background-color: var(--white);
    color: var(--fc-dark);
    font-size: var(--fs-body1);
    font-family: inherit;
    box-sizing: inherit;
    display: block;
  `;
  const ContentTitleDiv = styled.div`
    width: 70% !important;
    display: flex !important;
    border: solid 1px var(--black-075) !important;
    padding: 24px;
  `;
  const ContentTitleTextDiv = styled.div`
    display: flex !important;
    flex-direction: column !important;
    margin: -2px 0;
  `;
  const ContentTitleTextFlexDiv = styled.div`
    margin: 4px;
    flex-direction: column !important;
  `;
  const ContentTitleLabel = styled.div`
    font-size: 15px;
    color: #0c0d0e;
    font-family: inherit;
    font-weight: 600;
    padding: 0 2px;
  `;
  const ContentTitleMessageDiv = styled.div`
    margin: 0;
    padding: 0 2px;
    flex-basis: 75%;
    color: var(--fc-medium);
    font-size: 12px;
  `;

  return (
    <ContentTitleDiv>
      <ContentTitleTextDiv>
        <ContentTitleTextFlexDiv>
          <ContentTitleLabel htmlFor="title">Title</ContentTitleLabel>
          <ContentTitleMessageDiv>
            <label htmlFor="title">
              Be specific and imagine you’re asking a question to another
              person.
            </label>
          </ContentTitleMessageDiv>
        </ContentTitleTextFlexDiv>
        <ContentTitleTextFlexDiv style={{ position: "relative" }}>
          <ContentMessageInput
            id="title"
            name="title"
            type="text"
            maxLength="300"
            placeholder="e.g. Is there an R function for
              finding the index of an element in a vector?"
            className="s-input js-post-title-field ask-title-field"
            data-min-length="15"
            data-max-length="150"
          ></ContentMessageInput>
        </ContentTitleTextFlexDiv>
      </ContentTitleTextDiv>
    </ContentTitleDiv>
  );
};
export default AskQuestionTitle;
