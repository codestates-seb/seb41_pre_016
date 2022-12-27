import styled from "styled-components";
import TagComponent from "./TagComponent";
const AskQuestionTag = () => {
  const ContentBodyDiv = styled.div`
    margin-top: 15px;
    width: 70% !important;
    display: flex !important;
    border: solid 1px var(--black-075) !important;
    padding: 24px;
  `;
  const ContentBodyTextDiv = styled.div`
    display: flex !important;
    flex-direction: column !important;
    margin: -2px 0;
  `;
  const ContentBodyTextFlexDiv = styled.div`
    margin: 4px;
    flex-direction: column !important;
  `;
  const ContentBodyLabel = styled.div`
    font-size: 15px;
    color: #0c0d0e;
    font-family: inherit;
    font-weight: 600;
    padding: 0 2px;
  `;
  const ContentBodyMessageDiv = styled.div`
    margin: 0;
    padding: 0 2px;
    flex-basis: 75%;
    color: var(--fc-medium);
    font-size: 12px;
  `;
  const TagComponentDiv = styled.div`
    .ContentBodyTextFlexDiv {
      margin: 4px;
      flex-direction: column !important;
    }
    .TagBox {
      display: flex;
      align-items: center;
      flex-wrap: wrap;
      min-height: 50px;
      border: 1px solid rgba(0, 0, 0, 0.3);
      border-radius: 3px;
      width: 100%;
      margin: 0;
      padding: 0.6em 0.7em;
    }
    .DeleteButton {
      display: flex;
      justify-content: center;
      align-items: center;
      width: 16px;
      height: 16px;
      margin-left: 4px;
      background-color: inherit;
      color: hsl(205, 47%, 42%);
      align-self: center;
      border-radius: 3px;
      cursor: pointer;
      padding: 1px;
      font-size: 25px;
      :hover {
        background-color: hsl(205, 47%, 42%);
        color: #e1ecf4;
      }
    }
    .TagItem {
      max-height: 20px;
      margin: 2px;
      font-size: 13px;
      align-items: center;
      background-color: #e1ecf4;
      border-radius: 3px;
      color: hsl(205, 47%, 42%);
      padding: 4px;
      display: inline-flex;
      justify-content: center;
      min-width: 0;
      text-decoration: none;
      vertical-align: middle;
      white-space: nowrap;
    }
    .TagInput {
      width: max-content;
      margin: 0;
      padding: 0.6em 0.7em;
      border: none;
      background-color: var(--white);
      color: var(--fc-dark);
      font-size: var(--fs-body1);
      font-family: inherit;
      box-sizing: inherit;
      display: block;
    }
  `;
  return (
    <ContentBodyDiv>
      <ContentBodyTextDiv>
        <ContentBodyTextFlexDiv>
          <ContentBodyLabel htmlFor="tags">Tags</ContentBodyLabel>
          <ContentBodyMessageDiv>
            <label htmlFor="tags">
              Add up to 5 tags to describe what your question is about. Start
              typing to see suggestions.
            </label>
          </ContentBodyMessageDiv>
        </ContentBodyTextFlexDiv>
        <TagComponentDiv>
          <TagComponent />
        </TagComponentDiv>
      </ContentBodyTextDiv>
    </ContentBodyDiv>
  );
};
export default AskQuestionTag;
