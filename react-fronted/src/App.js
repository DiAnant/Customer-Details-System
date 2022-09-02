import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import AddOrUpdateCustomer from './components/AddOrUpdateCustomer';
import ListComponent from './components/ListComponent';
import HomePage from './components/HomePage';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import SearchComponent from './components/SearchComponent';

function App() {
  return (
    <div>
        <Router>
            <Header />
              <div className='container mt-5'>
                <Routes>
                  <Route path="/" exact element={<HomePage />} />
                  <Route path="/customers" element={ <ListComponent/> } />
                  <Route path="/add-update-customer/:id" element={ <AddOrUpdateCustomer/> } />
                  <Route path="/search-customers/" element={ <SearchComponent/> } />
                </Routes>
              </div>
            <Footer/>
        </Router>
    </div>
  );
}

export default App;
