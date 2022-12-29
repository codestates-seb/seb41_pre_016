import { createGlobalStyle } from 'styled-components';
import variables from './GlobalVariables';

const GlobalStyle = createGlobalStyle`
    * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
    }

    :root {
        font-family: "Noto Sans KR", sans-serif;
        font-weight: 400;
        ${variables}
    }
`;

export default GlobalStyle;
