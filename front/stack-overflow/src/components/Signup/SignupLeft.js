import styled from "styled-components";
import { ReactComponent as BadgeIcon } from "../../assets/badgeIcon.svg";
import { ReactComponent as QuestionIcon } from ".../../assets/questionIcon.svg";
import { ReactComponent as TagsIcon } from "../../assets/tagsIcon.svg";
import { ReactComponent as VoteIcon } from "../../assets/voteIcon.svg";

const SignupLeft = () => {
  const ContainDiv = styled.div`
    max-width: calc(var(--s-step) * 4) !important;
    font-size: var(--fs-body2) !important;
    margin-right: 48px !important;
    margin-bottom: 128px !important;
  `;
  const HeadlineH1 = styled.h1`
    line-height: var(--lh-xs) !important;
    font-size: 27px;
    margin-bottom: 32px !important;
    box-sizing: inherit;
  `;
  const ItemFlexDiv = styled.div`
    display: flex !important;
    margin-bottom: 24px;
    div {
      margin-right: 8px !important;
      display: block;
      font-size: 15px;
    }
  `;
  const ScriptDiv = styled.div`
    color: var(--fc-light) !important;
    font-size: var(--fs-body1) !important;
  `;
  const ScriptA = styled.a`
    color: var(--theme-link-color);
    text-decoration: none;
    cursor: pointer;
  `;

  return (
    <ContainDiv>
      <HeadlineH1>Join the Stack Overflow community</HeadlineH1>
      <ItemFlexDiv>
        <div>
          <QuestionIcon />
        </div>
        <div>Get unstuck — ask a question</div>
      </ItemFlexDiv>
      <ItemFlexDiv>
        <div>
          <VoteIcon />
        </div>
        <div>Unlock new privileges like voting and commenting</div>
      </ItemFlexDiv>
      <ItemFlexDiv>
        <div>
          <TagsIcon />
        </div>
        <div>Earn reputation and badges</div>
      </ItemFlexDiv>
      <ItemFlexDiv>
        <div>
          <BadgeIcon />
        </div>
        <div>Get unstuck — ask a question</div>
      </ItemFlexDiv>
      <ScriptDiv>
        <p>Collaborate and share knowledge with a private group for FREE.</p>
        <ScriptA>Get Stack Overflow for Teams free for up to 50 users</ScriptA>
      </ScriptDiv>
    </ContainDiv>
  );
};
export default SignupLeft;
