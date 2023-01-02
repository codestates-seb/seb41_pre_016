import styled from "styled-components";
import { ReactComponent as GlobalIconG } from "../../../../../../seb41_pre_016/front/stack-overflow/src/assets/globalIconG.svg";
import { ReactComponent as GlobalIconB } from "../../../../../../seb41_pre_016/front/stack-overflow/src/assets/globalIconB.svg";
import { ReactComponent as ExploreIcon } from "../../../../../../seb41_pre_016/front/stack-overflow/src/assets/exploreIcon.svg";
import { ReactComponent as OfficeBagIcon } from "../../../../../../seb41_pre_016/front/stack-overflow/src/assets/officeBagIcon.svg";
import { Link } from "react-router-dom";
const LeftSideBar = ({ page = "Home" }) => {
  const LeftSideBarDiv = styled.div`
    width: 164px;
    flex-shrink: 0;
    box-shadow: 0 0 0 hsl(210deg 8% 5% / 5%);
    transition: box-shadow ease-in-out 0.1s, transform ease-in-out 0.1s;
    transform: translateZ(0);
    position: relative;
    display: block;
    text-align: left;
    max-height: calc(100vh - 50px);
    a {
      text-decoration: none;
    }
    * {
      margin: 0;
      padding: 0;
      border: 0;
      font: inherit;
      font-size: 100%;
      vertical-align: baseline;
    }

    li {
      display: list-item;
      text-align: -webkit-match-parent;
    }
  `;
  const LeftSideBarStickyDiv = styled.div`
    position: sticky;
    width: auto;
    margin-bottom: 8px;
    overflow-y: auto;
    top: 50px;
    padding-top: 24px;
  `;
  const LeftSideBarOl = styled.ol`
    padding: 0;
    margin: 0 0 12px;
    list-style: none;
    li {
      position: relative;
    }
  `;

  const LeftSidePublicOl = styled.ol`
    margin-bottom: 12px;
    list-style: none;
    .title {
      text-transform: uppercase;
      color: var(--fc-light);
      font-size: var(--fs-fine);
      margin: 16px 0 4px 8px;
    }
  `;

  const LeftSideQuestionA = styled.div`
    font-weight: ${(props) => (page === props.page ? "bold" : "normal")};
    background: ${(props) =>
      page === props.page ? `var(--black-050)` : "none"};
    border-right: ${(props) =>
      page === props.page ? `3px solid hsl(27, 90%, 55%)` : "none"};
    color: ${(props) => (page === props.page ? `black` : "var(--fc-light)")};
    display: flex;
    padding: ${(props) =>
      props.page === "Question" ||
      props.page === "Collectives" ||
      props.page === "Team"
        ? "8px 6px 8px 8px"
        : "4px 4px 4px 30px"};
    padding: ${(props) => (props.page === "Home" ? "4px 4px 4px 8px" : " ")};
    line-height: var(--lh-xxl);
    font-size: var(--fs-body1);
    text-decoration: none;
    text-transform: capitalize;
    :hover {
      color: black;
    }
    .officeBag {
      background-color: var(--orange-400);
      border-radius: 3px;
      width: 16px;
      height: 16px;
      display: flex;
      justify-content: center;
    }
  `;

  const LeftSideQuestionSpan = styled.span`
    flex-shrink: 0;
    margin-top: -1px;
    margin-right: 4px;
    display: flex;
    align-items: center;
    height: 18px;
  `;

  return (
    <LeftSideBarDiv>
      <LeftSideBarStickyDiv>
        <nav>
          <LeftSideBarOl>
            <Link to="/">
              <li>
                <LeftSideQuestionA page="Home">Home</LeftSideQuestionA>
              </li>
            </Link>
            <li>
              <LeftSidePublicOl>
                <li className="title">PUBLIC</li>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Question">
                      <LeftSideQuestionSpan>
                        {page === "Question" ? (
                          <GlobalIconB />
                        ) : (
                          <GlobalIconG />
                        )}
                      </LeftSideQuestionSpan>
                      <LeftSideQuestionSpan>questions</LeftSideQuestionSpan>
                    </LeftSideQuestionA>
                  </li>
                </Link>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Tags">tags</LeftSideQuestionA>
                  </li>
                </Link>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Users">users</LeftSideQuestionA>
                  </li>
                </Link>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Companies">
                      companies
                    </LeftSideQuestionA>
                  </li>
                </Link>
                <li className="title">COLLECTIVES</li>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Collectives">
                      <LeftSideQuestionSpan>
                        <ExploreIcon />
                      </LeftSideQuestionSpan>
                      <LeftSideQuestionSpan>
                        Explore Collectives
                      </LeftSideQuestionSpan>
                    </LeftSideQuestionA>
                  </li>
                </Link>
                <li className="title">COLLECTIVES</li>
                <Link to="/">
                  <li>
                    <LeftSideQuestionA page="Team">
                      <LeftSideQuestionSpan className="officeBag">
                        <OfficeBagIcon />
                      </LeftSideQuestionSpan>
                      <LeftSideQuestionSpan>
                        Create free Team
                      </LeftSideQuestionSpan>
                    </LeftSideQuestionA>
                  </li>
                </Link>
              </LeftSidePublicOl>
            </li>
          </LeftSideBarOl>
        </nav>
      </LeftSideBarStickyDiv>
    </LeftSideBarDiv>
  );
};
export default LeftSideBar;
