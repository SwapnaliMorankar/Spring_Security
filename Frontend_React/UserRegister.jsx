import React, { useState } from 'react';
import axios from 'axios';
import '../CSS/UserRegister.css';

const UserRegister = () => {
  const [userData, setUserData] = useState({
    username: '',
    email: '',
    password: '',
    role: '',
  });

  const [message, setMessage] = useState(''); // State for success or error message

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:9000/api/register', userData, {
        headers: {
          'Content-Type': 'application/json', // Ensure proper headers
        },
      });
      console.log('User Data Submitted:', response.data);
      setMessage('User registration successful!');
      setUserData({
        username: '',
        email: '',
        password: '',
        role: '',
      }); // Reset the form
    } catch (error) {
      console.error('Error submitting user data:', error.response?.data || error.message);
      setMessage('User registration failed. Please try again.');
    }
  };

  return (
    <div className="user-register-container">
      <h2>User Registration</h2>
      {message && <p className="message">{message}</p>}
      <form className="user-register-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            name="username"
            value={userData.username}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            value={userData.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            name="password"
            value={userData.password}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="role">Role:</label>
          <input
            type="text"
            id="role"
            name="role"
            value={userData.role}
            onChange={handleChange}
            required
          />
        </div>

        <button type="submit" className="submit-button">Submit</button>
      </form>
    </div>
  );
};

export default UserRegister;
