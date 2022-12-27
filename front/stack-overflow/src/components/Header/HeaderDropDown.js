import styled from "styled-components";

const HeaderDropDown = () => {
  let reputation = "1";
  const DropDownDiv = styled.div`
    display: block;
    top: 51px;
    right: 331.5px;
    max-width: 1571.5px;
    width: 350px;
    height: 172px;
    font-family: var(--ff-sans);
    color: var(--black-700);
    font-size: var(--fs-caption);
    background-color: var(--white);
    box-shadow: var(--bs-sm);
    position: absolute;
    text-align: left;
    border-left: 1px solid var(--black-075);
    border-right: 1px solid var(--black-075);
    border-bottom: 1px solid var(--black-075);

    .header {
      background-color: var(--black-050);
      width: 100%;
      box-sizing: border-box;
      position: relative;
      clear: both;
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 8px 10px;
      max-height: 32px;
    }

    .fw-wrap {
      flex-wrap: wrap;
    }

    * {
      margin: 0;
      padding: 0;
    }

    li {
      list-style: none;
      margin-left: 0;
      line-height: 1.3;
    }
  `;
  const DropDownTitleA = styled.a`
    font-family: var(--ff-sans);
    font-weight: bold;
    text-transform: uppercase;
    font-size: var(--fs-fine);
    margin-bottom: 0;
    display: inline-block;
    color: var(--blue-600);
    :hover {
      color: var(--theme-link-color-hover);
    }
  `;
  const DropDownTitleModalContentDiv = styled.div`
    width: 100%;
    max-height: 300px;
    position: relative;
    background-color: ${(props) =>
      props.BG === "true" ? "var(--powder-050)" : "white"};

    :hover {
      background-color: ${(props) =>
        props.BG === "true" ? "var(--powder-100)" : "var(--black-100)"};
    }
  `;
  const DropDownTitleLi = styled.li`
    padding: 2px 7px;
    display: flex;
    justify-content: space-between;
  `;
  const DropDownTitleDiv = styled.div`
    padding: 8px;

    .left {
      display: flex;
      text-decoration: none;
    }

    .right {
      color: var(--blue-600);
      text-decoration: none;
      margin-left: 10px;

      :hover {
        color: var(--theme-link-color-hover);
      }
    }
  `;

  const DropDownColorSOColorImg = styled.div`
    width: 16px;
    height: 16px;
    vertical-align: top;
    margin: 0 4px;
    background-position: ${(props) =>
      props.CL === "true" ? "0 -6138px" : "0 -6156px;"};
    background-color: transparent;
    background-repeat: no-repeat;
    background-size: 16px;
    background-image: url("https://cdn.sstatic.net/Img/favicons-sprite16.png?v=1cfab30b280e");
  `;
  const DropDownItemSpan = styled.span`
    margin: 0 4px;
    flex: 1 auto;
    font-weight: ${(props) => (props.BOLD === "true" ? "bold" : "")};
    display: inline-block;
    color: var(--blue-600);
    font-family: inherit;
    font-size: 100%;
    vertical-align: baseline;

    :hover {
      color: var(--theme-link-color-hover);
    }
  `;
  const DropDownLIconDiv = styled.div`
    margin: 0 10px;
    display: flex;
    justify-content: center;
  `;
  const DropDownLIconSpan = styled.span`
    width: 10px;
    height: 10px;
    border: solid #b9c1c5;
    border-width: 0 0 1px 1px;
    display: inline-block;
  `;
  return (
    <DropDownDiv>
      <div className="header fw-wrap">
        <h3>
          <DropDownTitleA>current community</DropDownTitleA>
        </h3>
      </div>
      <DropDownTitleModalContentDiv BG="true">
        <ul>
          <DropDownTitleLi>
            <DropDownTitleDiv>
              <a className="left" href="/">
                <DropDownColorSOColorImg CL="true" />
                <DropDownItemSpan BOLD="true">Stack Overflow</DropDownItemSpan>
              </a>
            </DropDownTitleDiv>
            <DropDownTitleDiv>
              <a className="right" href="/">
                help
              </a>
              <a className="right" href="/">
                chat
              </a>
              <a className="right" href="/">
                log out
              </a>
            </DropDownTitleDiv>
          </DropDownTitleLi>
        </ul>
      </DropDownTitleModalContentDiv>
      <DropDownTitleModalContentDiv BG="true">
        <ul>
          <DropDownTitleLi>
            <DropDownTitleDiv>
              <a className="left" href="/">
                <DropDownLIconDiv>
                  <DropDownLIconSpan />
                </DropDownLIconDiv>
                <DropDownColorSOColorImg CL="false" />
                <DropDownItemSpan>Meta Stack Overflow</DropDownItemSpan>
              </a>
            </DropDownTitleDiv>
          </DropDownTitleLi>
        </ul>
      </DropDownTitleModalContentDiv>
      <div className="header">
        <h3>
          <DropDownTitleA>your Communities</DropDownTitleA>
        </h3>
        <DropDownTitleDiv>
          <a className="right" href="/">
            edit
          </a>
        </DropDownTitleDiv>
      </div>
      <DropDownTitleModalContentDiv BG="false">
        <ul>
          <DropDownTitleLi>
            <DropDownTitleDiv>
              <a className="left" href="/">
                <DropDownColorSOColorImg CL="true" />
                <DropDownItemSpan BOLD="false">Stack Overflow</DropDownItemSpan>
              </a>
            </DropDownTitleDiv>
            <DropDownTitleDiv>
              <p>{reputation}</p>
            </DropDownTitleDiv>
          </DropDownTitleLi>
        </ul>
      </DropDownTitleModalContentDiv>
    </DropDownDiv>
  );
};
export default HeaderDropDown;
