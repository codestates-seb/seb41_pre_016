import { userStore } from "../../store/zustandUser";
const UserButton = () => {
  const { name, email, password, postUser } = userStore();
  const signupButton = () => {
    const userObj = {
      name,
      email,
      password,
    };
    postUser("/user", userObj);
  };
  return <button onClick={signupButton}>Sign up</button>;
};
export default UserButton;
