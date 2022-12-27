import { userStore } from "../../store/zustandUser";
import { postStore } from "../../store/zustandFetch";
const UserButton = () => {
  const { name, email, password } = userStore();
  const { post } = postStore();
  const signupButton = () => {
    const userObj = {
      name,
      email,
      password,
    };
    post("/user", userObj);
  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
