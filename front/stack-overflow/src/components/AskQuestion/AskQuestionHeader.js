import styled from "styled-components";

const AskQuestionHeader = () => {
  const ContentNoticeDiv = styled.div`
    background: #ebf4fb;
    border-color: #a6ceed;
    color: #3b4045;
    border-style: solid;
    font-size: var(--fs-body1);
    border-radius: 3px;
    border-width: 1px;
    padding: 24px;
    width: 70% !important;
    margin-bottom: 15px;
    h2 {
      font-weight: 400 !important;
      font-size: 21px !important;
      margin-bottom: 8px !important;
    }
    p {
      font-size: 15px;
      margin-bottom: 15px !important;
    }
    h5 {
      font-weight: 600 !important;
      margin-bottom: 8px;
    }
    ul {
      list-style-type: disc;
      margin-left: 30px;
    }
  `;
  return (
    <ContentNoticeDiv>
      <h2>Writing a good question</h2>
      <p>
        You’re ready to ask a programming-related question and this form will
        help guide you through the process.
        <br />
        Looking to ask a non-programming question? See the topics here to find a
        relevant site.
      </p>
      <h5>Steps</h5>
      <ul>
        <li>Summarize your problem in a one-line title.</li>
        <li>Describe your problem in more detail.</li>
        <li>Describe what you tried and what you expected to happen.</li>
        <li>
          Add “tags” which help surface your question to members of the
          community.
        </li>
        <li>Review your question and post it to the site.</li>
      </ul>
    </ContentNoticeDiv>
  );
};
export default AskQuestionHeader;
