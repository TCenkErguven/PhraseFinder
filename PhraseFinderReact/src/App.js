import { BrowserRouter } from "react-router-dom";
import MainPage from "./pages/mainPage/mainPage";

export default function App() {
  return (
    <BrowserRouter>
      <MainPage />
    </BrowserRouter>
  );
}
