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
        <TagComponent />
      </ContentBodyTextDiv>
    </ContentBodyDiv>
  );
};
export default AskQuestionTag;
