import './App.css';
import CarForm from './components/CarForm/CarForm';
import Admin from './components/Admin/Admin';
import{BrowserRouter,Route} from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
    <div className="App">
    <Route exact path="/" component={CarForm} />
    </div>
    <div className="Admin">
      <Route exact path="/admin" component={Admin} />
    </div>
    </BrowserRouter>
  );
}

export default App;
