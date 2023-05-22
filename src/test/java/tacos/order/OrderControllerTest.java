package tacos.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import org.springframework.web.bind.support.SessionStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Errors errors;

    @Mock
    private SessionStatus sessionStatus;

    private OrderController orderController;

    @BeforeEach
    void setup() {
        orderController = new OrderController(orderRepository);
    }

    @Test
    void shouldSaveOrderAndRedirectToHomeAfterProcessingValidOrder() {
        // Given
        var order = new TacoOrder();
        when(errors.hasErrors()).thenReturn(false);

        // When
        String viewName = orderController.processOrder(order, errors, sessionStatus);

        //Then
        assertEquals("redirect:/", viewName);
        verify(errors).hasErrors();
        verify(orderRepository).save(order);
        verify(sessionStatus).setComplete();
    }

    @Test
    void shouldNotSaveOrderAndReturnOrderFormViewAfterProcessingInvalidOrder() {
        // Given
        var order = new TacoOrder();
        when(errors.hasErrors()).thenReturn(true);

        // When
        String viewName = orderController.processOrder(order, errors, sessionStatus);

        //Then
        assertEquals("orderForm", viewName);
        verify(errors).hasErrors();
        verify(orderRepository, never()).save(order);
        verify(sessionStatus, never()).setComplete();
    }
}