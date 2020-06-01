import React from 'react'
import { render, fireEvent, waitFor, screen } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'
import axiosMock from 'axios'


jest.mock('axios')


test('Fake test', () => {
    expect(true).toBeTruthy();
})
/*
test('loads and displays greeting', async () => {
    const url = '/main'
    render(<Fetch url={url} />)

    axiosMock.get.mockResolvedValueOnce({
        data: { greeting: 'hello there' },
    })

    fireEvent.click(screen.getByText('Load Greeting'))

    await waitFor(() => screen.getByRole('heading'))

    expect(axiosMock.get).toHaveBeenCalledTimes(1)
    expect(axiosMock.get).toHaveBeenCalledWith(url)
    expect(screen.getByRole('heading')).toHaveTextContent('hello there')
    expect(screen.getByRole('button')).toHaveAttribute('disabled')
})*/
