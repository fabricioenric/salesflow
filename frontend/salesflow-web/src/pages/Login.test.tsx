import { render, screen } from '@testing-library/react';
import { userEvent } from '@testing-library/user-event';
import { describe, it, expect, vi } from 'vitest';
import Login from './Login';
import { BrowserRouter } from 'react-router-dom';

// Mocking the useAuth hook
vi.mock('../auth/useAuth', () => ({
  useAuth: () => ({
    login: vi.fn(),
    logout: vi.fn(),
    isAuthenticated: false,
    user: null,
  }),
}));

describe('Login Page', () => {
  it('should render login form correctly', () => {
    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    expect(screen.getByPlaceholderText(/usuário/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/senha/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /acessar/i })).toBeInTheDocument();
  });

  it('should allow user to type in username and password fields', async () => {
    const user = userEvent.setup();
    render(
      <BrowserRouter>
        <Login />
      </BrowserRouter>
    );

    const usernameInput = screen.getByPlaceholderText(/usuário/i);
    const passwordInput = screen.getByPlaceholderText(/senha/i);

    await user.type(usernameInput, 'testuser');
    await user.type(passwordInput, 'password123');

    expect(usernameInput).toHaveValue('testuser');
    expect(passwordInput).toHaveValue('password123');
  });
});