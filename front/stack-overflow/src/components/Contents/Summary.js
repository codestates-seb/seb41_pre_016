import { useState } from "react";
import Tagbox from "../Buttons/Tagbox";
import styled, { css } from "styled-components";
import UserInfo from "./UserInfo";

const Container = styled.div`
  padding: 16px;
  position: relative;
  display: flex;
  border-bottom: 1px solid var(--black-075);
`;

const DetailInfo = styled.div`
  display: flex;
  gap: 6px;
  margin-right: 16px;
  margin-bottom: 4px;
  flex-direction: column;
  flex-shrink: 0;
  align-items: flex-end;
  font-size: 13px;

  div {
    align-items: center;
    border: 1px solid transparent;

    &:first-child {
      color: black;
    }

    &:nth-child(2) {
      padding: 3px 4px;
      border-radius: 3px;
      border: 1px solid rgb(47, 111, 68);
      color: rgb(47, 111, 68);
    }

    &:nth-child(3) {
      color: #6a737c;
    }

    /* &:nth-child(4) {
      color: white;
      padding: 3px 4px;
      border-radius: 3px;
      background-color: rgb(0, 116, 204);
    } */
  }
`;

const NoAnswered = styled.div`
  align-items: center;
  border: 1px solid transparent !important;
  color: var(--black-500) !important;
`;

const Title = styled.h3`
  font-size: 17px;
  margin-bottom: 5px;

  a {
    color: rgb(0, 99, 191);
    text-decoration-line: none;

    &:hover {
      color: #0a95ff;
      transition: all 0.3s;
    }
  }
`;

const PostInfo = styled.div`
  flex-grow: 1;
  max-width: 100%;
`;

const Excerpt = styled.div`
  color: hsl(210, 8%, 25%);
  hyphens: auto;
  font-size: 13px;
  line-height: 17px;
  margin-bottom: 7px;

  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  word-break: break-word !important;
`;

const Meta = styled.div`
  display: flex;
  align-items: start;
  justify-content: space-between;
  flex-wrap: wrap;
  column-gap: 6px;
  row-gap: 8px;
  font-size: 12px;
  min-height: 40px;
`;

const Summary = ({
  title,
  content,
  tags,
  vote,
  answer,
  views,
  name,
  userId,
}) => {
  return (
    <Container>
      <DetailInfo>
        <div>{vote} votes</div>
        {answer ? (
          <div>{answer} answers</div>
        ) : (
          <NoAnswered>0 answers</NoAnswered>
        )}
        <div>{views} views</div>
      </DetailInfo>
      <PostInfo>
        <Title>
          <a href="https://github.com/codestates-seb/seb41_pre_016">{title}</a>
        </Title>
        <Excerpt>{content}</Excerpt>
        <Meta>
          <Tagbox taglist={tags} />
          <UserInfo name={name} userId={userId} />
        </Meta>
      </PostInfo>
    </Container>
  );
};

export default Summary;
