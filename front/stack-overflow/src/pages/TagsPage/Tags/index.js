import Tag from '../../../components/Tag';
import styled from 'styled-components';

export const TagsContainer = styled.div`
  border: 1px solid #d6d9dc;
  border-radius: 3px;
  padding: 15px 13px 13px 13px;
`;
export const TagNameDiv = styled.div``;
export const TagInfoDiv = styled.div`
  margin-top: 20px;
`;
export const TagInfo = styled.p`
  font-size: 13px;
  color: #3b4044;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
`;
export const NoTagInfo = styled.p`
  margin-bottom: 80px;
  font-size: 13px;
  color: #3b4044;
  word-break: break-all;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
`;

export const TagCountDiv = styled.div`
  display: flex;
  font-size: 12px;
  color: #838c95;
  margin-top: 20px;
  gap: 10px;
`;

export const QuestionTotal = styled.div``;
export const TodayTotal = styled.div``;
export const WeekTotal = styled.div``;

const Tags = ({ list }) => {
  return (
    <TagsContainer>
      <TagNameDiv>
        <Tag name={list.tagName}></Tag>
      </TagNameDiv>
      <TagInfoDiv>
        {list.tagDescription ? (
          <TagInfo>{list.tagDescription}</TagInfo>
        ) : (
          <NoTagInfo></NoTagInfo>
        )}
      </TagInfoDiv>
      <TagCountDiv>
        <QuestionTotal>
          <div>{list.questionCountTotal}</div>
          <div>questions</div>
        </QuestionTotal>
        <TodayTotal>
          <div>{list.questionCountToday}</div>
          <div>asked today</div>
        </TodayTotal>
        <WeekTotal>
          <div>{list.questionCountWeek}</div>
          <div>this week</div>
        </WeekTotal>
      </TagCountDiv>
    </TagsContainer>
  );
};

export default Tags;
