import styled from "styled-components";
import ContentMessageTextarea from "./UtilComponents/ContentMessageTextarea";

const AskQuestionBody = () => {
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
    textarea {
      width: 98%;
      height: 13em;
      resize: none;
      margin: 0;
      border: 1px solid var(--bc-darker);
      border-radius: 3px;
      background-color: var(--white);
      color: var(--fc-dark);
      font-size: var(--fs-body1);
      font-family: inherit;
      box-sizing: inherit;
      display: block;
      padding: 12px;
    }
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
          <ContentBodyLabel htmlFor="body">Body</ContentBodyLabel>
          <ContentBodyMessageDiv>
            <label htmlFor="body">
              The body of your question contains your problem details and
              results. Minimum 30 characters.
            </label>
          </ContentBodyMessageDiv>
        </ContentBodyTextFlexDiv>
        <ContentBodyTextFlexDiv style={{ position: "relative" }}>
          <ContentMessageTextarea />
        </ContentBodyTextFlexDiv>
      </ContentBodyTextDiv>
    </ContentBodyDiv>
  );
};
export default AskQuestionBody;
